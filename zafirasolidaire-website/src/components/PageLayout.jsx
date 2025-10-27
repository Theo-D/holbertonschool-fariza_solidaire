import NavBar from "./NavBar";
import Footer from "./Footer";
const PageLayout = ({ children }) => {
  return (
    <div className="flex flex-col min-h-screen z-100">
      <NavBar />
      <main className="flex-1">
        {children}
      </main>
      <Footer />
    </div>
  );
};

export default PageLayout;
