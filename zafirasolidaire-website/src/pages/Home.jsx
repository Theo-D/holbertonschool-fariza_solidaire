import React, { useState } from 'react';
import { Zap, Shield, Sparkles, X } from 'lucide-react';
import { Link } from 'react-router-dom';
import ClothingCounter from '../components/ClothingCounter';
import ServicedCounter from '../components/ServicedCounter';
import PageLayout from '../components/PageLayout';

export default function Home() {


  return (
    <PageLayout>
      <div className="min-h-screen bg-linear-to-br from-slate-50 to-slate-100">
        {/* Hero Section */}
        <section className="pt-32 pb-20 px-4 sm:px-6 lg:px-8">
          <div className="max-w-7xl mx-auto">
            <div className="text-center">

              <h1 className="text-5xl sm:text-6xl lg:text-7xl font-bold mb-6 leading-tight">
                <span className="bg-linear-to-r from-[#42AAE1] via-[#E82B89] to-[#FCD916] bg-clip-text text-transparent">
                  Mettez toutes les chances
                </span>
                <br />
                <span className="text-gray-900">de votre côté</span>
              </h1>

              <p className="text-xl text-gray-600 mb-10 max-w-3xl mx-auto">
                Rejoignez des centaines de demandeurs d'emploi qui nous font confiance.
              </p>
            </div>

            {/* Decorative elements */}
            <div className="mt-16 relative">
              <div className="absolute top-0 left-1/4 w-72 h-72 rounded-full opacity-20 blur-3xl" style={{ backgroundColor: '#42AAE1' }}></div>
              <div className="absolute top-20 right-1/4 w-96 h-96 rounded-full opacity-20 blur-3xl" style={{ backgroundColor: '#E82B89' }}></div>
              <div className="absolute bottom-0 left-1/2 w-64 h-64 rounded-full opacity-20 blur-3xl" style={{ backgroundColor: '#FCD916' }}></div>
            </div>
          </div>
        </section>

        <section className="py-16 px-4 sm:px-6 lg:px-8 bg-white">
          <div className="max-w-7xl mx-auto">
            <div className="grid md:grid-cols-2 gap-6 max-w-4xl mx-auto">
              <ClothingCounter Label="Poids de vêtements donnés en kg" Icon="<Shirt />" />
              <ServicedCounter Label="Nombre de personnes accompagnées" Icon="<Users />" />
            </div>
          </div>
        </section>

        {/* Features Section */}
        <section id="features" className="py-20 px-4 sm:px-6 lg:px-8 bg-white">
          <div className="max-w-7xl mx-auto">
            <div className="text-center mb-16">
              <h2 className="text-4xl font-bold text-gray-900 mb-4">
                Pourquoi nous choisir ?
              </h2>
              <p className="text-xl text-gray-600">
                Des fonctionnalités pensées pour votre succès
              </p>
            </div>

            <div className="grid md:grid-cols-3 gap-8">
              <div className="group p-8 rounded-2xl bg-linear-to-br from-slate-50 to-white border border-slate-200 hover:shadow-xl transition-all hover:-translate-y-1">
                <div className="w-14 h-14 rounded-xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform" style={{ backgroundColor: '#42AAE1' }}>
                  <Zap className="w-7 h-7 text-white" />
                </div>
                <h3 className="text-2xl font-bold mb-3 text-gray-900">Rapide & Efficace</h3>
                <p className="text-gray-600">
                  Une performance optimale pour vous faire gagner un temps précieux au quotidien.
                </p>
              </div>

              <div className="group p-8 rounded-2xl bg-linear-to-br from-slate-50 to-white border border-slate-200 hover:shadow-xl transition-all hover:-translate-y-1">
                <div className="w-14 h-14 rounded-xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform" style={{ backgroundColor: '#E82B89' }}>
                  <Shield className="w-7 h-7 text-white" />
                </div>
                <h3 className="text-2xl font-bold mb-3 text-gray-900">Sécurisé</h3>
                <p className="text-gray-600">
                  Vos données sont protégées avec les dernières technologies de sécurité.
                </p>
              </div>

              <div className="group p-8 rounded-2xl bg-linear-to-br from-slate-50 to-white border border-slate-200 hover:shadow-xl transition-all hover:-translate-y-1">
                <div className="w-14 h-14 rounded-xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform" style={{ backgroundColor: '#FCD916' }}>
                  <Sparkles className="w-7 h-7 text-white" />
                </div>
                <h3 className="text-2xl font-bold mb-3 text-gray-900">Innovant</h3>
                <p className="text-gray-600">
                  Des fonctionnalités de pointe pour rester à la tête de votre secteur.
                </p>
              </div>
            </div>
          </div>
        </section>

        {/* CTA Section */}
        <section className="py-20 px-4 sm:px-6 lg:px-8">
          <div className="max-w-4xl mx-auto">
            <div className="rounded-3xl p-12 text-center text-white relative overflow-hidden" style={{ background: 'linear-gradient(135deg, #42AAE1 0%, #E82B89 50%, #FCD916 100%)' }}>
              <div className="relative z-10">
                <h2 className="text-4xl font-bold mb-4">
                  Prêt à commencer ?
                </h2>
                <p className="text-xl mb-8 opacity-95">
                  Rejoignez notre communauté et transformez votre façon de travailler
                </p>
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
