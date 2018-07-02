import React from "react";
import ProgressBar from "./ProgressBar.jsx";
import Header from "./Header.jsx";

class LandingPage extends React.Component {

    constructor() {
        super();

        this.state = {
            header:{heading:'SBMSInitializr'},
            progressBar:{value:56}
        };
    }

    render() {
        var headerStyle = {backgroundColor:'lightgreen',height:50,marginBottom:5,width:'100%'};
        var progressBarStyle = {marginBottom:5,width:'80%',height:'15'};

        return(<div className='landing-page-container'>
            <Header heading={this.state.header.heading} style={headerStyle} />
            <ProgressBar value={this.state.progressBar.value} style={progressBarStyle} />
        </div>);
    }
}

export default LandingPage;