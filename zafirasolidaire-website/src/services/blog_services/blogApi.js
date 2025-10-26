import api from "../strapiApi";

/**
 * Create new blog post
 * @param {Object} blogPost - The blog post data
 */
export const saveBlogPost = async (blogPost) => {
  // Strapi expects `{ data: {...} }`
  return await api.post("/blog-posts", { data: blogPost });
};

export const updateBlogPost = async (id, blogPost) => {
  return await api.put(`/blog-posts/${id}`, { data: blogPost });
};

export const getBlogPostById = async (id) => {
  return await api.get(`/blog-posts/${id}?populate=*`);
};

export const getAllBlogPosts = async () => {
  return await api.get(`/blog-posts?populate=*`);
};

export const deleteBlogPostById = async (id) => {
  return await api.delete(`/blog-posts/${id}`);
};
