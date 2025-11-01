import PageLayout from "../components/PageLayout";
import VolunteerHeroSection from "../components/VolunteerHeroSection";
const Volunteer = function () {
    return(
        <PageLayout>
            <div className="min-h-screen bg-linear-to-b from-cyan-700 to-cyan-950">
                <section className="py-20 px-4 sm:px-6 lg:px-8"></section>
                <VolunteerHeroSection/>
                <section className="py-20 pb-20 px-4 sm:px-6 lg:px-8"></section>
            </div>
        </PageLayout>
    );
}

export default Volunteer;
