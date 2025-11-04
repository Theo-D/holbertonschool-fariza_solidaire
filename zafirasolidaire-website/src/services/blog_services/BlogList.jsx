import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAllBlogPosts, deleteBlogPostById } from "./blogApi";
import { PLACEHOLDERS } from "../../components/imgPlaceholder";

const BlogList = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const STRAPI_REDIRECT = "http://localhost:1337/admin/content-manager/collection-types/api::blog-post.blog-post?page=1&pageSize=10&sort=documentId%3AASC";

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
    <div className="p-4">
      <h1 className="text-2xl font-bold mb-4 text-center">Articles de Blog</h1>

      {/* Blog posts list */}
      {posts.length === 0 ? (
        <div className="text-gray-700 bg-gray-100 p-4 rounded shadow text-center">
          Pas d'articles trouvés.
        </div>
      ) : (
        <ul className="list-none flex flex-wrap gap-4 justify-center">
          {posts.map((post) => {
            if (!post) return null;

            const imageUrl = post.photo?.url
              ? post.photo.url.startsWith("http")
                ? post.photo.url
                : `http://localhost:1337${post.photo.url}`
              : PLACEHOLDERS.blog;

            return (
              <li
                key={post.id}
                className="flex flex-col bg-white rounded-lg p-4 shadow w-80 border border-gray-300 overflow-hidden hover:shadow-lg transition"
              >
                {/* Image at the top */}
                <img
                  src={imageUrl}
                  alt={post.title || "Blog Post"}
                  className="w-full h-40 object-cover rounded-md mb-3"
                />

                {/* Text in the middle */}
                <div className="flex-1">
                  <h2 className="font-bold text-lg truncate">
                    {post.title || "Untitled Post"}
                  </h2>
                  <p className="text-sm text-gray-600 mb-2">
                    {post.createdAt
                      ? new Date(post.createdAt).toLocaleDateString()
                      : ""}
                  </p>
                </div>

                {/* Buttons at the bottom */}
                <div className="flex justify-between mt-3">
                  <button
                    onClick={() => navigate(`/blog/${post.documentId}`)}
                    className="flex-1 bg-blue-500 text-white px-3 py-1 rounded text-sm font-semibold hover:bg-blue-600 transition mr-2"
                  >
                    Vers l'article
                  </button>
                  <button
                    onClick={() => handleDelete(post.id)}
                    className="flex-1 bg-red-500 text-white px-3 py-1 rounded text-sm font-semibold hover:bg-red-600 transition"
                  >
                    Supprimer
                  </button>
                </div>
              </li>

            );
          })}
        </ul>
      )}
      <div className="my-20 flex justify-center">
        <button
          className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600 transition"
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
    </div>
  );

};

export default BlogList;
