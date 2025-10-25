// path: src/api/blog-post/content-types/blog-post/lifecycles.js
const skipLifecycle = new Set();
module.exports = {
  async afterCreate(event) {
  const { result } = event;
  if (result.externalId) return;

  // Fetch full post including media
  const fullPost = await strapi.db.query("api::blog-post.blog-post").findOne({
    where: { id: result.id },
    populate: ["photo"],
  });

  const payload = buildPayload(fullPost);

  try {
    console.log("🆕 afterCreate → POST to external API", payload);

    const res = await fetch("http://localhost:8080/blog_posts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });

    const createdPost = await res.json();

    skipLifecycle.add(result.id);
    await strapi.entityService.update("api::blog-post.blog-post", result.id, {
      data: { externalId: createdPost.id },
    });
    skipLifecycle.delete(result.id);
  } catch (err) {
    console.error("❌ Error in afterCreate:", err);
  }
},
  async afterUpdate(event) {
  const { result } = event;
  if (!result.externalId || skipLifecycle.has(result.id)) return;

  // Fetch full entity including media
  const fullPost = await strapi.db.query("api::blog-post.blog-post").findOne({
    where: { id: result.id },
    populate: ["photo"],
  });

  const payload = buildPayload(fullPost);

  try {
    skipLifecycle.add(result.id);
    console.log("Syncing post to external API:", payload);
    await fetch(`http://localhost:8080/blog_posts/${result.externalId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    skipLifecycle.delete(result.id);
  } catch (err) {
    console.error(err);
  }
},
};

// Helper function to build the payload for POST or PUT
function buildPayload(result) {
  const textBody = Array.isArray(result.textBody)
    ? result.textBody.map(b => b.children?.map(c => c.text).join(' ')).join('\n')
    : result.textBody;

  return {
    title: result.title,
    textBody,
    author: result.author,
    photoUrl: result.photo?.url ? `http://localhost:1337${result.photo.url}` : null,
  };
}
