import { useEffect, useState } from "react";
import { BlocksRenderer } from "@strapi/blocks-react-renderer";
import { getBlogPostById } from "../services/blog_services/blogApi";
import PageLayout from "../components/PageLayout";
import { useParams } from "react-router-dom";

export default function Blog() {
  const [post, setPost] = useState(null);
  const documentId = useParams();
  const baseUrl = "http://localhost:1337"

  useEffect(() => {
    getBlogPostById("cwjz6ex9kt09rib4uwie8a60")
      .then((res) => {
        console.log(res.data.data); // The actual post object
        setPost(res.data.data);
      })
      .catch((err) => {
        console.error("Failed to fetch post:", err);
      });
  }, []);

  if (!post) {
    return (
      <div className="flex h-screen items-center justify-center">
        <p className="text-xl text-gray-600">Loading...</p>
      </div>
    );
  }

  return (
    <PageLayout>
      <article className="max-w-3xl mx-auto py-12 mt-12 text-center">
        <h1 className="text-5xl mb-8 text">{post.title}</h1>
        <img
          className="max-w-2xl mx-auto"
          src={baseUrl + post.photo.url}
          alt={post.photo.alternativeText || ""}
        >
        </img>
        <div className="prose mx-auto ">
          {post.textBody && post.textBody.length > 0 ? (
            <BlocksRenderer content={post.textBody} />
          ) : (
            <p>No content available</p>
          )}
        </div>
      </article>
    </PageLayout>
  );
}
