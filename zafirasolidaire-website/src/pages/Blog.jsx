import BlogEditor from "../components/BlogEditor";
function Blog() {
  return (
    <div className="flex h-screen items-center justify-center ">
      <h1 className="text-9xl text-center text-red-800">
        <BlogEditor/>
      </h1>
    </div>
  );
}


export default Blog
