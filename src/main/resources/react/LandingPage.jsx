import React from "react";
import ProgressBar from "./ProgressBar.jsx";
import Header from "./Header.jsx";
import FolderSelector from "./FolderSelector.jsx";
import Actions from "./Actions.jsx";
import MainSection from "./MainSection.jsx";
import ExpandMainSection from "./ExpandMainSection.jsx";
import SbmsWebSocket from "./SbmsWebSocket.jsx";

class LandingPage extends React.Component {

    constructor() {
        super();

        this.toggleExpandState = this.toggleExpandState.bind(this);

        this.state = {
            header: {heading: 'SBMSInitializr'},
            progressBar: {value: 56},
            expandMainSection: {expand: false, action: this.toggleExpandState}
        };
    }

    render() {
        var headerStyle = {backgroundColor:'lightgreen',height:50,marginBottom:5,width:'100%'};
        var progressBarStyle = {marginBottom:10,width:'95%',height:15,display:'inline-block'};
        var folderSelectorStyle = {width: '70%', marginBottom:5, display:'inline-block'};
        var actionsStyle = {width:'29%',height:15,display:'inline-block',borderStyle:'dotted',borderColor:'lightgreen'};
        var mainSectionStyle = {width:'100%',borderStyle:'inset',borderColor:'lightgreen',height:500};
        var expandMainSectionStyle = {width:'5%',display:'inline-table'};
        var lineDivStyle1 = {};
        var lineDivStyle2 = {};

        var isDisplayed = !this.state.expandMainSection.expand;
        return(<div className='landing-page-container'>
            {isDisplayed && <Header heading={this.state.header.heading} style={headerStyle} />}
            {isDisplayed && <div className='lp-line-div-container' style={lineDivStyle1}>
                <FolderSelector style={folderSelectorStyle} />
                <Actions style={actionsStyle} />
            </div>}
            <div className='lp-line-div-container' style={lineDivStyle2}>
                <ProgressBar value={this.state.progressBar.value} style={progressBarStyle} />
                <ExpandMainSection expand={this.state.expandMainSection.expand} style={expandMainSectionStyle} action={this.state.expandMainSection.action} />
            </div>
            <MainSection style={mainSectionStyle} />
            <SbmsWebSocket onRecieve={this.onRecieveFromServer}/>
        </div>);
    }

    toggleExpandState() {
        var newState = this.state;
        newState.expandMainSection.expand = !newState.expandMainSection.expand;
        this.setState(newState);
    }

    onRecieveFromServer() {
        //TODO update the state accordingly
    }
}

export default LandingPage;