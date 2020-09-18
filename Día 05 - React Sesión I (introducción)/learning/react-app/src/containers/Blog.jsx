import React, {Component} from "react";
import PostsList from "../components/PostsList/PostsList";

class Blog extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: []
        }
    }

    componentDidMount() {
        this.getPosts();
    }

    getPosts = () => {
        fetch('http://jsonplaceholder.typicode.com/posts')
            .then((response) => response.json())
            .then(data => {
                // console.log(data);
                this.setState(prevState => ({
                    ...prevState,
                    posts: data
                }))
            })
            .catch(error => console.log(error));
    }

    goPost = (id) => {
        console.log("ID: ", id);
        window.location.href = "http://localhost:8080/post/" + id;
    }

    render() {
        const {posts} = this.state;
        return <div>
            <h1>Blog</h1>
            <PostsList onClickHandle={(id) => this.goPost(id)} posts={posts}/>
        </div>
    }
};

export default Blog;