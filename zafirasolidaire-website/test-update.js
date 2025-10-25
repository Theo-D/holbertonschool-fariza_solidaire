const STRAPI_URL = "http://localhost:1337"; // ⚠️ Ensure this is your correct Strapi URL and port
const BLOG_POST_ID = 159; // The ID of the post to update
const AUTH_TOKEN = 'b8a1ebd9ac87c792536172cb68f5f0b6606316298554a29c781e3d76832c9578946dc5c865f73f512a33ce3257fe75680dbe056ebb3adc9676f0c7e387870fba81e3f2f89bb8bd48d1f86d8e9265969120a4c5776bd6da72192a054ef25f94ab488fec152cefa22618e047717ee9c03360413df9796c520255ea02a4d05eecd5'; // ⚠️ Replace null with your Bearer Token if Strapi is protected (e.g., 'Bearer <your-token>')

async function safelyUpdatePost(postId, newTitle) {
  let existingPost;

  // 1. GET: Fetch the existing post data with necessary population
  try {
    const getHeaders = {
        "Content-Type": "application/json",
    };
    if (AUTH_TOKEN) getHeaders['Authorization'] = AUTH_TOKEN;

    // Test 2: Explicitly request ALL locales to bypass any locale filtering
const getUrl = `${STRAPI_URL}/api/blog-posts/${postId}?populate=*&locale=all`;

    console.log(`🔎 Attempting GET request to: ${getUrl}`);

    const getResponse = await fetch(getUrl, {
        method: 'GET',
        headers: getHeaders
    });

    const data = await getResponse.json();

    if (!getResponse.ok || !data.data) {
        // If the server returns a 404 or the data is empty, log the raw response
        console.error(`❌ GET Request failed (Status: ${getResponse.status}).`);
        console.error("   Strapi Response:", JSON.stringify(data, null, 2));
        return;
    }

    existingPost = data.data; // The actual post data is under 'data.data' in Strapi v4

    // Check if the photo is actually linked and get its ID
    const photoId = existingPost.attributes.photo?.data?.id || null;

    console.log(`✅ Fetched existing post (ID: ${existingPost.id}). Title: "${existingPost.attributes.title}"`);
    console.log(`   Photo Relationship ID found: ${photoId === null ? 'None' : photoId}`);

    // 2. CONSTRUCT SAFE PAYLOAD: Build the update payload
    const updateData = {
      title: newTitle,
      // CRITICAL FIX: Explicitly include the existing photo ID to preserve the link.
      photo: photoId
      // Add other relational IDs (tags, categories) here if you have them!
    };

    // Wrap the data in the required Strapi 'data' object
    const payload = { data: updateData };

    // 3. PUT: Execute the safe update
    const putHeaders = { "Content-Type": "application/json" };
    if (AUTH_TOKEN) putHeaders['Authorization'] = AUTH_TOKEN;

    const putResponse = await fetch(`${STRAPI_URL}/api/blog-posts/${postId}`, {
      method: "PUT",
      headers: putHeaders,
      body: JSON.stringify(payload),
    });

    if (putResponse.ok) {
      console.log(`\n🎉 PUT SUCCESS! Check your logs. The 'beforeDelete' hook should NOT have fired for ID ${postId}.`);
    } else {
      const errorBody = await putResponse.json();
      console.error(`\n❌ PUT FAILED with status ${putResponse.status}:`, errorBody);
    }
  } catch (err) {
    console.error("\n❌ Unhandled error during update process:", err.message);
  }
}

// === Run the test ===
// Change the title slightly each time you run it to confirm the update works.
safelyUpdatePost(BLOG_POST_ID, "New Title - Safe Update Attempt");
