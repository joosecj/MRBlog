import PostCard from "components/PostCard";
import { useParams } from "react-router-dom";

function Post() {
    const params = useParams();
    return (
        <PostCard postId={`${params.postId}`} />
    );
}

export default Post;