import axios from 'axios';
import { useEffect, useState } from 'react';
import { Blog } from 'types/blog';
import { BASE_URL } from 'utils/requests';

type Props = {
    postId : string;
}

function PostCard({ postId } : Props) {
    const [post, setPost] = useState<Blog>();

    useEffect(() => {
        axios.get(`${BASE_URL}/posts/${postId}`)
        .then(response => {
            setPost(response.data);
        });
    }, [postId]);

    return (
        <div className="container">
        <div className="mrblog-card-container">
          <div className="mrblog-card">
            <h2>{post?.title}</h2>
            <p className="mrblog-line">{post?.titleDescription}</p>
            <p className="mrblog-date">{post?.dateTime}</p>
            <div className="mrblog-card-description">
              <p>{post?.description}</p>
            </div>
          </div>
        </div>
        </div>
    )
}

export default PostCard