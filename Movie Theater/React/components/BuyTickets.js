const BuyTickets = ({ handleClose, show, children }) => {
    const showHideClassName = show ? "modal d-block" : "modal- d-none";

    return (
        <div className={showHideClassName}>
            <div className="buy-tickets-container">
                {children}
                <a href="javascript:;" className="buy-tickets-close" onClick={handleClose}>
                    Cancel
                </a>
            </div>
        </div>
    );
};

export default BuyTickets;