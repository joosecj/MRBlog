import'./styles.css';
import { Link } from "react-router-dom";
import { Blog } from "types/blog";

type Props = {
    blog: Blog,
};

function BlogCard({ blog } : Props){

    return (
        <div className="container">
        <div className="mrblog-card-container">
          <div className="mrblog-card">
            <h2>{blog.title}</h2>
            <p className="mrblog-line">{blog.titleDescription}</p>
            <p className="mrblog-date">{blog.dateTime}</p>
            <div className="mrblog-card-description">
              <p>{blog.description}</p>
              <Link to={'/form/${blog.id}'}>
                <div className="btn btn-primary mrblog-btn">Ler mais</div>
              </Link>
            </div>
          </div>
        </div>
      </div>
    )

}
export default BlogCard;