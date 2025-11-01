import React, { forwardRef, useState, useRef } from "react";

const ContactFormModal = forwardRef(({ onClose }, ref) => {
    const modalRef = useRef(null);
    const [subject, setSubject] = useState("Tu es:");

    const handleSend = () => {
    // URL-encode the subject for safety
    const mailtoLink = `mailto:fariza.solidaire@email.com?subject=${encodeURIComponent("Bénévolat: " + subject)}`;
    window.location.href = mailtoLink;
    handleClose();
    };

    return (
        <dialog ref={ref} className="modal">
            <div className="modal-box flew-col">
                <p>Adresse mail: <b>fariza.solidaire@email.com</b></p>

                <select defaultValue={subject} className="select select-neutral my-5" onChange={(e) => setSubject(e.target.value)}>
                    <option disabled={true}>Tu es:</option>
                    <option>Conseiller·e en image</option>
                    <option>Professionnel·le de la beauté</option>
                    <option>Photographe</option>
                    <option>Professionnel·le des RH ou de l’insertion</option>
                    <option>Professionnel·le de la santé mentale</option>
                </select>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-x-12">
                    <button
                        type="button"
                        className="btn mt-4"
                        onClick={handleSend}
                    >
                    Envoyer
                    </button>

                    <button
                        type="button"
                        className="btn mt-4"
                        onClick={onClose} // <-- Calls parent handler
                    >
                    Fermer
                    </button>
                </div>
            </div>
        </dialog>
    );
});

export default ContactFormModal;
