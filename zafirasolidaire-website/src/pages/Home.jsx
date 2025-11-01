import React, { useState } from 'react';
import { Zap, Shield, Sparkles, X } from 'lucide-react';
import { Link } from 'react-router-dom';
import ClothingCounter from '../components/ClothingCounter';
import ServicedCounter from '../components/ServicedCounter';
import PageLayout from '../components/PageLayout';
import PartnerCardsList from '../components/PartnerCardsList';

export default function Home() {


  return (
    <PageLayout>
      <div className="min-h-screen bg-linear-to-b from-cyan-700 to-cyan-950">
        {/* Hero Section */}
        <section className="pt-32 pb-20 px-4 sm:px-6 lg:px-8">
          <div className="max-w-7xl mx-auto">
            <div className="text-center">

              <h1 className="text-5xl sm:text-6xl lg:text-7xl font-bold mb-6 leading-tight">
                <span className="bg-linear-to-r from-[#42AAE1] via-[#E82B89] to-[#FCD916] bg-clip-text text-transparent">
                  Mettez toutes les chances
                </span>
                <br />
                <span className="bg-linear-to-r from-[#42AAE1] via-[#E82B89] to-[#FCD916] bg-clip-text text-transparent">
                  de votre côté
                </span>
              </h1>
              <h2 className='font-bold text-3xl text-cyan-300 mb-3'>Chez Zafira, pas de discours en l’air.</h2>
              <p className="text-xl text-yellow-200 mb-10 max-w-3xl mx-auto">
                On agit, on soutient, on élève.
                <b> Parce que l’apparence ne devrait jamais être un frein, mais un levier pour prendre sa
                  place.</b>
              </p>
            </div>

            {/* Decorative elements */}
            <div className="mt-16 relative">
              <div className="absolute top-20 left-1/4 w-72 h-72 rounded-full opacity-20 blur-3xl" style={{ backgroundColor: '#42AAE1' }}></div>
              <div className="absolute top-20 right-1/4 w-96 h-96 rounded-full opacity-20 blur-3xl" style={{ backgroundColor: '#E82B89' }}></div>
              <div className="absolute bottom-20 left-1/2 w-64 h-64 rounded-full opacity-20 blur-3xl" style={{ backgroundColor: '#FCD916' }}></div>
            </div>
          </div>
        </section>

        {/* LEFT HAND SIDE CARD */}
        <div className="card card-side bg-base-100 mr-10 ml-10 mb-20 shadow-md ">
          <figure className='h1/5 shrink-0'>
            <img
              src="https://media.gettyimages.com/id/1591572504/fr/photo/des-femmes-daffaires-joyeuses-se-serrant-la-main-dans-la-salle-de-r%C3%A9union.jpg?s=612x612&w=0&k=20&c=nS3XDIn0n5fS07FH1yEU6jdNeEaEmwEkpO2jodR9d6o="
              alt="Movie"
              className='object-cover w-full h-full rounded-r-none'
              />
          </figure>
          <div className="card-body">
            <article className='prose lg:prose-xl text-lg'>
              <h1 className="card-title text-3xl">Notre vision</h1>
              <p className='line-clamp-6 mt-3 mb-3'> Chez <b>Zafira Solidaire</b>, on ne laisse personne sur le banc de touche à cause de son look.
                Fondée en 2022 à Aulnay-sous-Bois, notre mission est claire : <b>bousculer les codes</b> pour
                construire une société où <b>l’apparence n’est plus une barrière</b> mais une arme pour
                avancer.
                <b>Notre combat : démonter les discriminations liées à l'image.</b>
                On ne devrait jamais rater une opportunité parce qu'on n'a pas la bonne tenue ou le bon
                style. Chez nous, on booste l’estime de soi, on équipe nos bénéficiaires pour qu’ils
                <b>débarquent en entretien avec la bonne dégaine et la bonne attitude.</b>
                Costumes, tailleurs, conseils en image, photos pro...
                On donne les outils, on donne l’énergie, <b>mais c’est eux qui prennent leur destin en main.</b>
              </p>
              <h2 className='font-semibold'>Chez Zafira, on croit en:</h2>
              <ul className='mb-5 indent-5'>
                <li>🔥 Le pouvoir d’un look qui impose le respect</li>
                <li>🔥 L’importance d’une confiance qui défonce les barrières</li>
                <li>🔥 Une solidarité qui donne du style ET de la force</li>
              </ul>
              <h2 className='font-semibold'>Notre but?</h2>
              <p className='line-clamp-6 mt-1 mb-3 indent-5'> Voir chaque personne quitter Zafira la tête haute, prête à tout déchirer sur son chemin</p>
            </article>
            <div className="card-actions justify-end">
              {/* <button className="btn btn-primary">Watch</button> */}
            </div>
          </div>
        </div>

        {/* RIGHT HAND SIDE CARD */}
        <div className="card flex-row-reverse bg-base-100 mr-10 ml-10 mt-20 mb-20 shadow-md rounded-xl overflow-hidden">
          <figure className="w-1/3 shrink-0 rounded-none! m-0!">
            <img
              src="https://media.gettyimages.com/id/1591572504/fr/photo/des-femmes-daffaires-joyeuses-se-serrant-la-main-dans-la-salle-de-r%C3%A9union.jpg?s=612x612&w=0&k=20&c=nS3XDIn0n5fS07FH1yEU6jdNeEaEmwEkpO2jodR9d6o="
              alt="Movie"
              className="object-cover w-full h-full rounded-none!"
            />
          </figure>

          <div className="card-body">
            <article className="prose lg:prose-xl text-lg">
              <h1 className="card-title text-3xl">Nos Services : On équipe, on booste, on propulse</h1>
              <ul className='mt-5 mb-5'>
                <li>
                   <h3 className='font-bold'>✅ Vestiaire professionnel</h3>
                   <p className='mt-1 mb-4 indent-5' >
                     Des costumes, tailleurs, chaussures, ceintures et accessoires pour <b>se pointer aux
                     entretiens prêt·e à marquer des points.</b>
                   </p>
                </li>
                <li>
                   <h3 className='font-bold'>✅ Atelier de maquillage naturel</h3>
                   <p className='mt-1 mb-4 indent-5'>
                     Pour un look soigné et fidèle à soi-même — <b>pas de déguisement</b>, juste la meilleure
                     version de toi.
                   </p>
                </li>
                <li>
                   <h3 className='font-bold'>✅ Coaching image personnalisé </h3>
                   <p className='mt-1 mb-4 indent-5' >
                     Un accompagnement pour apprendre à <b>maîtriser son style, son allure, son impact</b>
                   </p>
                </li>
                <li>
                   <h3 className='font-bold'>✅ Shooting photo pro pour CV & LinkedIn </h3>
                   <p className='mt-1 mb-2 indent-5' >
                      Parce que la première impression compte. <b>On te tire le portrait comme un boss</b> pour que
                      ton profil respire la confiance.
                   </p>
                </li>
              </ul>
            </article>
          </div>
        </div>

        <div className="flex w-full h-[400px] gap-6">
          {/* Person Card - 1/3 width */}
          <div className="w-1/3 bg-white flex flex-row rounded-xl shadow-lg ml-10 overflow-hidden">
            <div className="w-1/2 m-3">
              <img
                src="https://marisca.fr/wp-content/uploads/2025/08/PORTRAIT-MARISCA-scaled.jpg"
                alt="Jane Doe"
                className="w-full h-full object-cover rounded-l-xl"
              />
            </div>
          <div className="w-1/2 flex flex-col justify-center p-4">
            <h2 className="text-xl font-bold">Fariza SOUILAH</h2>
            <p className="text-gray-600">Conseillère en image & photographe</p>
            <p className="text-gray-500 text-sm mt-1">Fondatrice de Zafira Solidaire</p>
            <div className="mt-4 text-gray-700">
              <p>A PROPOS</p>
              <p>
                Passionate about building scalable web apps and mentoring junior
                developers.
              </p>
            </div>
          </div>
        </div>


          {/* Content Div - 2/3 width */}
          <div className="w-2/3 bg-white p-6 rounded-xl shadow-lg flex flex-col justify-between mr-10">
          <div className="grid grid-cols-4 grid-rows-1 gap-2 h-3/5">
              <img
                src="https://images.unsplash.com/photo-1761054172958-bed78dc0e0be?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=687"
                alt="Project 1"
                className="w-full h-full object-cover rounded-lg"
              />
              <img
                src="https://images.unsplash.com/photo-1746469460743-b0d8951ad8c1?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHx0b3BpYy1mZWVkfDI0fHRvd0paRnNrcEdnfHxlbnwwfHx8fHw%3D&auto=format&fit=crop&q=60&w=600"
                alt="Project 2"
                className="w-full h-full object-cover rounded-lg"
              />
              <img
                src="https://images.unsplash.com/photo-1535957998253-26ae1ef29506?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=872"
                alt="Project 3"
                className="w-full h-full object-cover rounded-lg"
              />
              <img
                src="https://plus.unsplash.com/premium_photo-1678917827737-aa7dc0b0c46f?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1740"
                alt="Project 4"
                className="w-full h-full object-cover rounded-lg"
              />
            </div>
            <div>
              <h2 className="text-2xl font-semibold mb-4">A propos de l'association</h2>
              <p className="text-gray-700 mb-4">
                Description des personnes en lien avec l'association, des photos d'ahérents, de salariés...
              </p>
            </div>
          </div>
        </div>

        <section className="py-16 px-4 sm:px-6 lg:px-8 ">
          <div className="max-w-7xl mx-auto">
            <div className="grid grid-cols-2 gap-24 ">
              <ClothingCounter Label="Poids de vêtements donnés en kg" Icon="<Shirt />" />
              <ServicedCounter Label="Nombre de personnes accompagnées" Icon="<Users />" />
            </div>
          </div>
        </section>

        <div className="flex gap-10 mr-10 ml-10 h-50">
        {/* First card/link */}
        <a
          href="https://www.helloasso.com/associations/zafira-vestiaire-solidaire/formulaires/3"
          className="flex w-1/2 bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-xl transition-all"
        >
          {/* 25% Image */}
          <div className="w-1/4">
            <img
              src="https://cdn.helloasso.com/img/photos/collectes/%20mg%207754-5c9caa4537f442bda754a7d5c566c425.jpg?width=220&height=220&quality=80&img_format=webp"
              alt="Person 1"
              className="w-full h-full object-cover"
            />
          </div>
          <div className="w-2/3 flex items-center justify-center p-4">
            <span className="font-bold text-4xl text-center text-pink-900">Je fais un don à ZAFIRA VESTIAIRE SOLIDAIRE</span>
          </div>
        </a>

        {/* Second card/link */}
        <a
          href="https://www.helloasso.com/associations/zafira-vestiaire-solidaire/adhesions/adhesion-zafira-solidaire"
          className="flex w-1/2 bg-white rounded-xl shadow-lg overflow-hidden hover:shadow-xl transition-all"
        >
          <div className="w-1/4">
            <img
              src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJKXlBEvE0FjzVN0FLg6BMB80zpRXrzHQb_w&s"
              alt="Person 2"
              className="w-full h-full object-cover"
            />
          </div>
          <div className="w-2/3 flex items-center justify-center p-4">
            <span className="font-bold text-4xl text-center text-pink-900">J'adhère à ZAFIRA VESTIAIRE SOLIDAIRE</span>
          </div>
        </a>
      </div>



        {/* LEFT HAND SIDE CARD */}
        <div className="card card-side bg-base-100 mr-10 ml-10  mt-20 mb-5 shadow-md ">
          <figure className='h1/5 shrink-0'>
            <img
              src="https://media.gettyimages.com/id/1591572504/fr/photo/des-femmes-daffaires-joyeuses-se-serrant-la-main-dans-la-salle-de-r%C3%A9union.jpg?s=612x612&w=0&k=20&c=nS3XDIn0n5fS07FH1yEU6jdNeEaEmwEkpO2jodR9d6o="
              alt="Movie"
              className='object-cover w-full h-full rounded-r-none'
              />
          </figure>
          <div className="card-body">
            <article className='prose lg:prose-xl text-lg'>
              <h1 className="card-title text-3xl">Nos Partenaires 💥</h1>
              <p className='line-clamp-6 mt-3 mb-3 indent-5'>
                Chez <b>Zafira Solidaire</b>, on croit que <b>rien de grand ne se construit seul</b>.
                Si aujourd’hui on peut redonner de la confiance, de la dignité et du style à celles et ceux qui
                en ont besoin, c’est aussi <b>grâce à des alliés qui croient en notre vision et qui passent à
                l’action avec nous.</b>
              </p>
              <p className='line-clamp-6 mt-3 mb-3'>
                🤝 <b>Associations, entreprises, collectivités, structures d’accompagnement,
                commerçants engagés…</b>
                Nos partenaires, ce sont des gens qui ne se contentent pas de belles paroles. Ce sont des
                acteurs du changement, des bâtisseurs de confiance, des faiseurs d’opportunités
              </p>
              <h2 className='font-semibold text-2xl mb-3'>💬 Ce qu’ils nous apportent ?</h2>
              <ul className='mb- indent-5'>
                <li>Des vêtements de qualité.</li>
                <li>L’importance d’une confiance qui défonce les barrières</li>
                <li>Des relais vers les publics en insertion</li>
                <li>Du financement, du matériel, des idées… et surtout, <b>de la force</b></li>
              </ul>
            </article>
            <div className="card-actions justify-end">
              {/* <button className="btn btn-primary">Watch</button> */}
            </div>
          </div>
        </div>

        <div className="w-screen h-24  bg-white flex items-center justify-center shadow-md mb-5 mt-5">
          <h1 className="text-cyan-900 text-4xl font-bold">🌟 Merci à celles et ceux qui marchent à nos côtés</h1>
        </div>

        <div className='mt-5 mb-5'>
          <PartnerCardsList/>
        </div>

        <section className="py-20 px-4 sm:px-6 lg:px-8">
          <div className="max-w-4xl mx-auto">
            <div className="rounded-3xl p-12 text-center text-white relative overflow-hidden" style={{ background: 'linear-gradient(135deg, #42AAE1 0%, #E82B89 50%, #FCD916 100%)' }}>
              <div className="relative z-10">
                <h2 className="text-4xl font-bold mb-6">Vous voulez rejoindre l’aventure ?</h2>
                <p className="text-xl font-semibold mb-3 opacity-95">Vous êtes une entreprise, une fondation, une collectivité, un acteur social ou un particulier engagé ?</p>
                <p className="text-xl mb-3 opacity-95">Vous croyez en une solidarité concrète, badass et durable ?</p>
                <p className="text-xl mb-3 opacity-95">Alors contactez-nous:</p>
                <button
                  className="btn btn-soft btn-warning w-75"
                  onClick={() => {
                    navigator.clipboard.writeText("fariza.solidaire@email.com");
                    alert("Adresse mail copiée dans le presse-papier");
                  }}
                >
                  <p className='text-xl'>fariza.solidaire@email.com</p>
                </button>

              </div>
              {/* Decorative circles */}
              <div className="absolute top-0 right-0 w-64 h-64 bg-white opacity-10 rounded-full -mr-32 -mt-32"></div>
              <div className="absolute bottom-0 left-0 w-48 h-48 bg-white opacity-10 rounded-full -ml-24 -mb-24"></div>
            </div>
          </div>
        </section>

      </div>
    </PageLayout>
  );
}
