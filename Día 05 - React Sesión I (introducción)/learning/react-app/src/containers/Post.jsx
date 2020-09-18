import React, {Component} from "react";

class Post extends Component {
    constructor(props) {
        super(props);
        this.state = {
            idPost: null
        }
    }

    componentDidMount() {
        console.log(this.props.match.param.id);
        this.setState(prevState => ({
            ...prevState,
            idPost: this.props.match.param.id
        }))
    }

    render() {
        const {idPost} = this.state;
        return <div>
            <h1>Post</h1>
            <p>Post id: {idPost}</p>
        </div>
    }
}

export default Post;