import React from "react";
import ProgressBar from "./ProgressBar.jsx";
import Header from "./Header.jsx";
import FolderSelector from "./FolderSelector.jsx";

class LandingPage extends React.Component {

    constructor() {
        super();

        this.state = {
            header:{heading:'SBMSInitializr'},
            progressBar:{value:56},
            folderSelector:{placeHolder:'Path to the micro service cluster(eg : D:\\code\\serviceCluster)'}
        };
    }

    render() {
        var headerStyle = {backgroundColor:'lightgreen',height:50,marginBottom:5,width:'100%'};
        var progressBarStyle = {marginBottom:10,width:'80%',height:15};
        var folderSelectorStyle = {width: '70%'};

        return(<div className='landing-page-container'>
            <Header heading={this.state.header.heading} style={headerStyle} />
            <ProgressBar value={this.state.progressBar.value} style={progressBarStyle} />
            <FolderSelector placeHolder={this.state.folderSelector.placeHolder} style={folderSelectorStyle} />
        </div>);
    }
}

export default LandingPage;