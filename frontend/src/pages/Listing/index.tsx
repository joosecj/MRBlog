import axios from "axios";
import BlogCard from 'components/BlogCard';
import Pagination from 'components/Pagination';
import { useEffect, useState } from 'react';
import { BlogPage } from "types/blog";
import'./styles.css';

function Listing() {
  const [pageNumber, setPageNumber] = useState(0);

  useEffect(() => {
    axios.get(`$BASE_URL}/posts/posts?size=10&page=1&sort=title`)
    .then(response => {
      const data = response.data as BlogPage;
      console.log(data);
      setPageNumber(data.number);
    });
  },[]);

    return (
      <><h1 className="news-title">Últimas notícias</h1>
      <section id="news">
        <BlogCard />
        <p>{pageNumber}</p>
        <Pagination />
      </section></>
    );
}

export default Listing;