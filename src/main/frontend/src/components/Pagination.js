import { useState } from "react";
import "../css/pagination.css";

// total(데이터 총 갯수), limit(한 페이지에 보여줄 갯수)
const Pagination = ({ total, limit, page, setPage }) => {
  const numPages = Math.ceil(total / limit); // 총 페이지 수는 올림해야 함

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
        {Array(numPages)
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
          disabled={page === numPages}
        >
          &gt;
        </button>
      </div>
    </>
  );
};

export default Pagination;
