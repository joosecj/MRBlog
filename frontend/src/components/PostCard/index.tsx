import './styles.css';
import axios from 'axios';
import { Link } from "react-router-dom";
import { useEffect, useState } from 'react';
import { Blog } from 'types/blog';
import { BASE_URL } from 'utils/requests';


type Props = {
  postId: string;
}

function PostCard({ postId }: Props) {
  const [post, setPost] = useState<Blog>();

  useEffect(() => {
    axios.get(`${BASE_URL}/api/posts/v1/${postId}`)
      .then(response => {
        setPost(response.data);
      });
  }, [postId]);


  return (
    <div className="container">
      <div className="mrblog-card-container">
        <div className="mrblog-card">
          <h2>{post?.title}</h2>
          <p className='mrblog-subtitle'>{post?.titleDescription}</p>
          <p className="mrblog-line"></p>
          <p className="mrblog-date">Criado em {post?.dateTime}</p>
          <div className="mrblog-card-description">
            <p>{post?.description}</p>
            <div className="mrblog-user-card">
              <img className="mrblog-user-image" src={post?.user.urlImage} alt={post?.user.name} />
              <div className='mrblog-user-detail'>
                <p>Autor(a): {post?.user.name}</p>
                <p>Cadastrado desde: {post?.user.registrationDate}</p>
              </div>
            </div>
          </div>
          <Link to='/'>
            <div className="btn btn-primary mrblog-btn">
              Voltar
            </div>
          </Link>
        </div>
      </div>
    </div>
  )
}

export default PostCard;