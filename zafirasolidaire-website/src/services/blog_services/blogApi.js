import api from "../api";

//TODO: Maybe add photo method

export const saveBlogPost = async () => {
  return await api.post("/blog_posts");
}

export const updateBlogPost = async (id, blogPost) => {
  return await api.put(`/blog_posts/${id}`, blogPost);
}

export const getBlogPostById = async (id) => {
  return await api.get(`/blog_posts/${id}`);
}

export const getAllBlogPosts = async () => {
  return await api.get(`/blog_posts`);
}

export const deleteBlogPostById = async (id) => {
  return await api.delete(`/blog_posts/${id}`);
}
