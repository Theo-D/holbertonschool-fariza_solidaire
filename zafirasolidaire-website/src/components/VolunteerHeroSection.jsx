import React, { useRef } from 'react';
import VolunteerContactModal from "./VolunteerContactModal";

export default function VolunteerHeroSection() {

    const modalRef = useRef(null);

    const handleOpen = () => {
        if (modalRef.current) {
        modalRef.current.showModal();
        }
    };

    const handleClose = () => {
        if (modalRef.current) {
        modalRef.current.close();
        }
    };

    return(
            <div className="card card-side bg-base-100 mr-10 ml-10 mb-20 shadow-md h-4/5">
          <figure className='h1/5 shrink-0'>
            <img
              src="https://media.gettyimages.com/id/1591572504/fr/photo/des-femmes-daffaires-joyeuses-se-serrant-la-main-dans-la-salle-de-r%C3%A9union.jpg?s=612x612&w=0&k=20&c=nS3XDIn0n5fS07FH1yEU6jdNeEaEmwEkpO2jodR9d6o="
              alt="Movie"
              className='object-cover w-full h-full rounded-r-none'
              />
          </figure>
          <div className="card-body">
            <article className='prose lg:prose-xl text-lg'>
                <h1 className="card-title text-3xl">💥 Rejoins l’aventure Zafira</h1>
                <h2 className="card-title text-2xl">
                    Deviens bénévole et aide-nous à changer des vies — avec style, cœur et compétences.
                </h2>
                <p className='line-clamp-6 mt-3 mb-3'>
                    Chez <b>Zafira Solidaire</b>, on ne fait pas “juste” de la solidarité.
                    On redonne confiance, on casse les barrières, on prépare des jeunes à <b>se présenter au monde avec assurance</b>.
                    Et pour ça, on a besoin de <b>toi</b>.
                </p>
                <h2 className="card-title text-2xl">
                    Qui on cherche?
                </h2>
                <p className='line-clamp-6 mt-3 mb-3'>
                    Tu es passionné·e par ton métier, tu as envie de t’impliquer dans un projet humain, utile et
                    stylé ? Viens mettre ton talent au service d’une cause qui a du sens.
                    Nous recherchons des <b>bénévoles engagé·e·s</b> dans les domaines suivants :
                </p>
                <ul className="mb-5 indent-5">
                    <li className="mb-5">
                        <h3 className="font-semibold">Conseillères en image</h3>
                        <ul className="pl-5">
                            <li>Diplômées ou en cours de certification</li>
                            <li>Pour accompagner nos bénéficiaires dans la valorisation de leur image pro</li>
                        </ul>
                    </li>

                    <li className="mb-5">
                        <h3 className="font-semibold">Professionnel·le·s de la beauté</h3>
                        <ul className="pl-5">
                            <li>Make-up artist, coiffeur·se, barbier·e</li>
                            <li>Pour sublimer sans travestir, et révéler les personnalités</li>
                        </ul>
                    </li>

                    <li className="mb-5">
                        <h3 className="font-semibold">Photographes</h3>
                        <ul className="pl-5">
                            <li>Pour réaliser des portraits pros stylés pour CV et LinkedIn</li>
                            <li>Shooting express ou mini-studio, on s’adapte à ton style</li>
                        </ul>
                    </li>

                    <li className="mb-5">
                        <h3 className="font-semibold">Professionnel·le·s des RH ou de l’insertion</h3>
                        <ul className="pl-5">
                            <li>
                                Pour aider nos bénéficiaires à se préparer aux entretiens, optimiser leurs candidatures, et
                                affûter leur discours pro
                            </li>
                        </ul>
                    </li>

                    <li className="mb-5">
                        <h3 className="font-semibold">Professionnel·le·s de la santé mentale</h3>
                        <ul className="pl-5">
                            <li>Psychologues, thérapeutes, coachs certifié·e·s</li>
                            <li>
                                Pour soutenir l’estime de soi, la gestion du stress, et accompagner avec bienveillance les
                                parcours fragilisés
                            </li>
                        </ul>
                    </li>
                </ul>

                <h2 className="card-title text-2xl">
                    Ce qu’on attend de toi (au-delà des compétences)
                </h2>
                <p className='line-clamp-6 mt-3 mb-3'>
                    Chez Zafira, <b>le savoir-faire est important, mais le savoir-être l’est encore plus</b>.
                    Nos bénévoles partagent nos valeurs et notre vision d’une solidarité moderne, inclusive et
                    respectueuse.
                    Nous cherchons des personnes :
                </p>
                <ul className="mb-5 indent-5">
                    <li>
                        <p>🌱 <b>Bienveillantes</b> – Qui savent écouter et encourager sans impose.</p>
                    </li>
                    <li>
                        <p> ❤️ <b>Empathiques</b> – Capables de comprendre sans juger.</p>
                    </li>
                    <li>
                        <p>🎧 <b>À l’écoute</b> – Chaque histoire mérite attention, chaque personne mérite respect.</p>
                    </li>
                    <li>
                        <p>🚫 <b>Sans jugement</b> – Peu importe le passé, on regarde vers l’avenir.</p>
                    </li>
                    <li>
                        <p>
                            🔒 <b>Respectueuses de la confidentialité</b> – Ce qui est partagé avec toi reste protégé : <b>la confiance est la
                                base de tout ce que nous construisons</b>.
                        </p>
                    </li>
                </ul>

                <h2 className="card-title text-2xl">
                    💡 Pourquoi devenir bénévole chez Zafira ?
                </h2>
                <ul className="mb-5 indent-5">
                    <li>
                        <p>✅ Pour <b>transmettre ton savoir-faire</b> à celles et ceux qui en ont besoin.</p>
                    </li>
                    <li>
                        <p>✅ Pour <b>voir un déclic dans les yeux</b> d’un·e jeune qui reprend confiance.</p>
                    </li>
                    <li>
                        <p>✅ Pour <b>agir concrètement</b>, sur le terrain, à ta manière.</p>
                    </li>
                    <li>
                        <p>✅ Et pour faire partie d’une team <b>ultra engagée, bienveillante et badass</b>.</p>
                    </li>
                </ul>

                <h2 className="card-title text-2xl">
                    🤝 Comment ça marche ?
                </h2>
                <p>Tu choisis: </p>
                <ul className="mb-5 indent-5">
                    <li>
                        <p><b>La fréquence </b>: une fois par mois, à chaque atelier, ou ponctuellement.</p>
                    </li>
                    <li>
                        <p><b>Le format</b> : en présentiel, ou à distance selon le besoin.</p>
                    </li>
                    <li>
                        <p><b>Ton mode d’action</b> : individuel, collectif, one-shot ou suivi.</p>
                    </li>
                </ul>
                <p>Nous, on t’accueille, on t’intègre, on t’équipe. Tu fais partie de la team</p>

              <h2 className='font-semibold'>🚀 Prêt·e à faire la diff ?</h2>
              <button
                      className="btn btn-soft btn-info w-55 pb-1"
                      onClick={handleOpen}
                    >
                      <p className='text-xl'>Écris-nous</p>
                    </button>
                    <VolunteerContactModal ref={modalRef} onClose={handleClose} />
            </article>
            <div className="card-actions justify-end">
            </div>
          </div>
        </div>
    );
}
