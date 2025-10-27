package com.hbtn.zafirasolidaire.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.hbtn.zafirasolidaire.model.Partner;
import com.hbtn.zafirasolidaire.repository.PartnerRepository;

@Component
public class PartnersInitializer implements CommandLineRunner {

    private final PartnerRepository partnerRepository;

    public PartnersInitializer(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final String IMG_URL = "https://www.centre-inffo.fr/content/uploads/2018/09/logo-partenaire-region-auvergne-rhone-alpes-rvb-bleu-gris.png";
        if (partnerRepository.count() == 0) {
            Partner[] partners = {
                new Partner()
                    .setName("Acme Corp")
                    .setHomepageUrl("https://www.acme.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Globex")
                    .setHomepageUrl("https://www.globex.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Initech")
                    .setHomepageUrl("https://www.initech.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Umbrella Corp")
                    .setHomepageUrl("https://www.umbrella.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Wayne Enterprises")
                    .setHomepageUrl("https://www.wayneenterprises.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Stark Industries")
                    .setHomepageUrl("https://www.starkindustries.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Oscorp")
                    .setHomepageUrl("https://www.oscorp.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Tyrell Corporation")
                    .setHomepageUrl("https://www.tyrell.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Cyberdyne Systems")
                    .setHomepageUrl("https://www.cyberdyne.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Wonka Industries")
                    .setHomepageUrl("https://www.wonka.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Hooli")
                    .setHomepageUrl("https://www.hooli.com")
                    .setLogoUrl(IMG_URL),

                new Partner()
                    .setName("Soylent Corp")
                    .setHomepageUrl("https://www.soylent.com")
                    .setLogoUrl(IMG_URL)
            };

            for (Partner partner : partners) {
                partnerRepository.save(partner);
            }

            System.out.println("Initialized 12 partners.");
        }
    }
}
