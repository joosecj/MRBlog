import BlogCard from 'components/BlogCard';
import Pagination from 'components/Pagination';
import'./styles.css';

function Listing() {

    return (
      <><h1 className="news-title">Últimas notícias</h1>
      <section id="news">
        <BlogCard />
        <Pagination />
      </section></>
    );
}

export default Listing;