const DialogModal = ({ selectedEvent, setSelectedEvent }) => {
    const closeModal = () => {
      const dialog = document.getElementById('event_modal');
      if (dialog) dialog.close();
      setSelectedEvent(null);
    };

    return (
      <dialog id="event_modal" className="modal modal-bottom sm:modal-middle">
        <div className="modal-box">
          {selectedEvent ? (
            <>
              <h3 className="font-bold text-lg">Evenement sélectionné</h3>
              <p>{selectedEvent.category}</p>
              <p>{selectedEvent.date}</p>
              <p>{selectedEvent.capacity}</p>
            </>
          ) : (
            <p>No event selected</p>
          )}
          <div className="modal-action">
            <button className="btn" onClick={closeModal}>
              Close
            </button>
          </div>
        </div>
      </dialog>
    );
  };

  export default DialogModal;
