const AssignRescue = ({ handleClose, show, children }) => {
    const showHideClassName = show ? "modal d-block" : "modal- d-none";

    return (
        <div className={showHideClassName}>
            <div className="assign-rescue-container">
                {children}
                <a href="javascript:;" className="assign-rescue-close" onClick={handleClose}>
                    Cancel
                </a>
            </div>
        </div>
    );
};

export default AssignRescue;