import { useEffect, useState } from "react";
import { BlocksRenderer } from "@strapi/blocks-react-renderer";
import { getBlogPostById } from "../services/blog_services/blogApi";
import PageLayout from "../components/PageLayout";
import { useParams } from "react-router-dom";
import { PLACEHOLDERS } from "../components/imgPlaceholder";

export default function Blog() {
  const [post, setPost] = useState(null);
  const {documentId} = useParams();
  const baseUrl = "http://localhost:1337"

  useEffect(() => {
    getBlogPostById(documentId)
      .then((res) => {
        console.log(res.data.data);
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
          src={post?.photo?.url ? baseUrl + post.photo.url : PLACEHOLDERS.blog}
          alt={post?.photo?.alternativeText || ""}
        />
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
