import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllBlogPosts, deleteBlogPostById } from "./blogApi";
import { PLACEHOLDERS } from "../../components/imgPlaceholder";

const BlogList = ({ redirectUrl = "/some-page" }) => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const STRAPI_REDIRECT = "http://localhost:1337/admin/content-manager/collection-types/api::blog-post.blog-post?page=1&pageSize=10&sort=title%3AASC";

  // Fetch blog posts from Strapi
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const json = await getAllBlogPosts();
        console.log("STRAPI RESPONSE: ", json.data.data);
        setPosts(Array.isArray(json.data.data) ? json.data.data : []);
      } catch (err) {
        console.error("Error fetching posts:", err);
        setPosts([]);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, []);

  // Delete a post
  const handleDelete = async (id) => {
    if (!window.confirm("Êtes-vous sûr(e) de vouloir supprimer cet article?")) return;

    try {
      await deleteBlogPostById(id);
      setPosts((prev) => prev.filter((post) => post.id !== id));
    } catch (err) {
      console.error("Error deleting post:", err);
    }
  };

  if (loading) return <p>Loading posts...</p>;

  return (
    <div>
      <h1>Blog Posts</h1>

      <button
        style={{ marginBottom: "1rem" }}
        onClick={() => navigate(redirectUrl)}
      >
        Go to Redirect Page
      </button>

      {posts.length === 0 ? (
        <p>No posts found.</p>
      ) : (
        <ul style={{ listStyle: "none", padding: 0 }}>
          {console.log("POSTS: ", posts)}
          {posts.map((post) => {
          if (!post) return null;

          return (
            <li
              key={post.id}
              style={{
                border: "1px solid #ccc",
                borderRadius: "8px",
                padding: "1rem",
                marginBottom: "1rem",
              }}
            >
              <h2>{post.title || "Untitled Post"}</h2>
              <p>{post.createdAt ? new Date(post.createdAt).toLocaleDateString() : ""}</p>

              {post.photo?.url && (
                <img
                  src={
                    post.photo?.url
                      ? post.photo.url.startsWith("http")
                        ? post.photo.url
                        : `http://localhost:1337${post.photo.url}`
                      : PLACEHOLDERS.blog
                  }
                />
              )}

              <div style={{ marginTop: "0.5rem" }}>
                <button
                  onClick={() => navigate(`/blog/${post.id}`)}
                  style={{
                    marginRight: "0.5rem",
                    padding: "0.5rem 1rem",
                    borderRadius: "4px",
                    cursor: "pointer",
                  }}
                >
                  View Article
                </button>

                <button
                  onClick={() => handleDelete(post.id)}
                  style={{
                    backgroundColor: "red",
                    color: "white",
                    border: "none",
                    padding: "0.5rem 1rem",
                    borderRadius: "4px",
                    cursor: "pointer",
                  }}
                >
                  Delete
                </button>
              </div>
            </li>
          );
        })}
        </ul>
      )}
      <button
        className="btn btn-secondary"
        onClick={() => {
          if (STRAPI_REDIRECT) {
            window.open(STRAPI_REDIRECT, '_blank', 'noopener,noreferrer');
          } else {
            console.warn("URL de redirection manquante.");
          }
        }}
      >
        Nouvel article
      </button>
    </div>
  );
};

export default BlogList;
