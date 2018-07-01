import React from "react";
import ProgressBar from "./ProgressBar.jsx";

class LandingPage extends React.Component {

    constructor() {
        super();

        this.state = {
            progressBar:{width:500,height:25,value:56}};
    }

    render() {

        return(<div className='landing-page-container'>
            <ProgressBar width={this.state.progressBar.width} height={this.state.progressBar.height} value={this.state.progressBar.value}/>
        </div>);
    }
}

export default LandingPage;