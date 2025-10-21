import Footer from "../components/Footer";
import Header from "../components/Header";
import HeroCentered from "../components/HeroCentered";
import Hero from "../components/Hero";
import HeroReverse from "../components/HeroReverse";

function Home() {
    return(
        <>
        <Header>
        <h1>This is the Home Page</h1>
        </Header>

        <body>
            <HeroCentered></HeroCentered>
            <Hero></Hero>
            <HeroReverse></HeroReverse>
        </body>

        <Footer></Footer>
        </>
    );
}

export default Home
