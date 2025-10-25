import { useEffect } from "react";

const BlogRedirect = () => {
  useEffect(() => {
    window.location.replace("http://localhost:1337/admin/content-manager/collection-types/api::blog-post.blog-post?page=1&pageSize=10&sort=Title%3AASC");
  }, []);

  return <p>Redirecting to admin...</p>;
};

export default BlogRedirect;
