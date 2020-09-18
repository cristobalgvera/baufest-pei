import React from "react";
import {Button} from "react-bootstrap";

const PostsList = (props) => {
    const {posts, onClickHandle} = props;

    return (
        <>
            {
                posts &&
                posts.map(post => (
                    <ul>
                        <li key={post.id}>
                            <h4>{post.title}</h4>
                            <p>{post.body}</p>
                            <Button onClick={() => onClickHandle(post.id)} variant={"dark"}>See more</Button>
                        </li>
                    </ul>
                ))
            }

        </>
    )
};

export default PostsList;