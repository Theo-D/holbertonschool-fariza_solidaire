// path: src/api/blog-post/content-types/blog-post/lifecycles.js

module.exports = {
  async afterCreate(event) {
    const { result } = event;


    try {
      // 🧠 Clean up the text body (if it’s structured)
      const textBody = Array.isArray(result.testBody)
        ? result.testBody.map(b => b.children?.map(c => c.text).join(' ')).join('\n')
        : result.testBody;

      const payload = {
        title: result.Title,
        textBody,
        author: result.author,
        photoUrl: result.photo?.url
          ? `http://localhost:1337${result.photo.url}`
          : null,
      };

// Log the object to check it
console.log("📤 Payload being sent to Spring Boot:", payload);

      // 1️⃣ Send the new post to Spring Boot (no ID)
      const res = await fetch("http://localhost:8080/blog_posts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          title: result.Title,
          textBody,
          author: result.author,
          photoUrl: result.photo?.url
            ? `http://localhost:1337${result.photo.url}`
            : null,
        }),
      });

      // 2️⃣ Parse the response from Spring Boot
      if (!res.ok) {
        throw new Error(`Spring Boot API returned ${res.status}`);
      }
      const createdPost = await res.json();

      // 3️⃣ Save the Spring Boot ID back into Strapi
      await strapi.db.query("api::blog-post.blog-post").update({
        where: { id: result.id },
        data: { externalId: createdPost.id },
      });

      console.log(`✅ Synced new blog post to Spring Boot (ID: ${createdPost.id})`);
    } catch (err) {
      console.error("❌ Failed to sync with Spring Boot:", err);
    }
  },

  async afterUpdate(event) {
    const { result } = event;

    if (!result.externalId) {
      console.warn(`⚠️ BlogPost ${result.id} has no externalId — skipping update`);
      return;
    }

    try {
      const textBody = Array.isArray(result.testBody)
        ? result.testBody.map(b => b.children?.map(c => c.text).join(' ')).join('\n')
        : result.testBody;

      await fetch(`http://localhost:8080/blog_posts/${result.externalId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          title: result.Title,
          textBody,
          author: result.author,
          photoUrl: result.photo?.url
            ? `http://localhost:1337${result.photo.url}`
            : null,
        }),
      });

      console.log(`✅ Updated blog post ${result.externalId} in Spring Boot`);
    } catch (err) {
      console.error("❌ Failed to update blog post in Spring Boot:", err);
    }
  },
};
