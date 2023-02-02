import axios from "axios";
import BlogCard from 'components/BlogCard';
import Pagination from 'components/Pagination';
import { useEffect, useState } from 'react';
import { BlogPage } from "types/blog";
import { BASE_URL } from "utils/requests";
import'./styles.css';

function Listing() {
  const [pageNumber, setPageNumber] = useState(0);

  const [page, setPage] = useState<BlogPage>({
    content: [],
    last: true,
    totalPages: 0,
    totalElements: 0,
    size: 10,
    number: 0,
    first: true,
    numberOfElements: 0,
    empty: true,
});

  useEffect(() => {
    axios.get(`${BASE_URL}/api/posts/v1?size=10&page=${pageNumber}&sort=title`)
    .then(response => {
      const data = response.data as BlogPage;
      setPage(data);
    });
  },[pageNumber]);

  const handlePageChance = (newPageNumber : number) => {
    setPageNumber(newPageNumber)
  }

    return (
      <>
      <h1 className="news-title">Últimas notícias</h1>
      <section id="news">
        {page.content.map(blog => (
          <div key={blog.id} className="mrblog-container">
            <BlogCard blog={blog}/>
          </div>
        ))}
        <Pagination page={page} onChange={handlePageChance} />
      </section></>
    );
}

export default Listing;