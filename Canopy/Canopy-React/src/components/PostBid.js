const PostBid = ({ handleClose, show, children }) => {
    const showHideClassName = show ? "modal d-block" : "modal- d-none";

    return (
        <div className={showHideClassName}>
            <div className="post-bid-container">
                {children}
                <a href="javascript:;" className="post-bid-close" onClick={handleClose}>
                    Cancel
                </a>
            </div>
        </div>
    );
};

export default PostBid;