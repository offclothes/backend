import "../css/pagination.css";

const ShopDetailPagination = ({ totalPage, page, setPage }) => {
  return (
    <>
      <div className="Nav">
        <button
          className="btn"
          onClick={() => setPage(page - 1)}
          disabled={page === 1}
        >
          &lt;
        </button>
        {Array(totalPage)
          .fill()
          .map((_, i) => (
            <button
              className="btn"
              key={i + 1}
              onClick={() => setPage(i + 1)}
              aria-current={page === i + 1 ? "page" : undefined}
            >
              {i + 1}
            </button>
          ))}
        <button
          className="btn"
          onClick={() => setPage(page + 1)}
          disabled={page === totalPage}
        >
          &gt;
        </button>
      </div>
    </>
  );
};

export default ShopDetailPagination;
