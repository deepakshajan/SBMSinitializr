import React from "react";
import ProgressBar from "./ProgressBar.jsx";
import Header from "./Header.jsx";

class LandingPage extends React.Component {

    constructor() {
        super();

        this.state = {
            header:{heading:'SBMSInitializr',height:50,width:'100%',position:'relative'},
            progressBar:{width:500,height:15,value:56}};
    }

    render() {
        var headerStyle = {backgroundColor:'lightgreen',height:this.state.header.height,marginBottom:5};

        return(<div className='landing-page-container'>
            <Header heading={this.state.header.heading} style={headerStyle} />
            <ProgressBar width={this.state.progressBar.width} height={this.state.progressBar.height} value={this.state.progressBar.value}/>
        </div>);
    }
}

export default LandingPage;