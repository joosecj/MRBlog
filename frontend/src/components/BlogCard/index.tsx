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
          <Link to={`/post/${blog.id}`}>
          <h2>{blog.title}</h2>
          </Link>
            <p className="mrblog-line">{blog.titleDescription}</p>
            <p className="mrblog-date">Autor - {blog.user.name} | Categoria - {blog.category.name} | Criado em {blog.dateTime}</p>
            <div className="mrblog-card-description">
              <p>{blog.description}</p>
            </div>
          </div>
        </div>
        </div>
    )

}
export default BlogCard;