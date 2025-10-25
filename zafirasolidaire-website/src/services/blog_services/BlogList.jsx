import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const BlogList = ({ redirectUrl = "/some-page" }) => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  // Fetch blog posts from API
  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const res = await fetch("http://localhost:8080/blog_posts"); // Replace with your API
        const data = await res.json();
        setPosts(data);
      } catch (err) {
        console.error("Error fetching posts:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, []);

  // Delete a post
  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this post?")) return;

    try {
      await fetch(`http://localhost:8080/blog_posts/${id}`, {
        method: "DELETE",
      });
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
          {posts.map((post) => (
            <li
              key={post.id}
              style={{
                border: "1px solid #ccc",
                borderRadius: "8px",
                padding: "1rem",
                marginBottom: "1rem",
              }}
            >
              <h2>{post.title}</h2>
              <p>{new Date(post.createDate).toLocaleDateString()}</p>

              <div style={{ marginTop: "0.5rem" }}>
                <button
                  onClick={() => navigate(`/blog/${post.id}`)} // Replace with your article page
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
          ))}
        </ul>
      )}
    </div>
  );
};

export default BlogList;
