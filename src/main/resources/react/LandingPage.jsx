import React from "react";
import ProgressBar from "./ProgressBar.jsx";
import Header from "./Header.jsx";
import FolderSelector from "./FolderSelector.jsx";
import Actions from "./Actions.jsx";
import MainSection from "./MainSection.jsx";
import ExpandMainSection from "./ExpandMainSection.jsx";
import SbmsWebSocket from "./SbmsWebSocket.jsx";
import Helper from "./utils/Helper.jsx";

class LandingPage extends React.Component {

    constructor() {
        super();

        this.toggleExpandState = this.toggleExpandState.bind(this);
        this.setFolderSelectorValue = this.setFolderSelectorValue.bind(this);
        this.onRecieveFromServer = this.onRecieveFromServer.bind(this);

        this.state = {
            header: {heading: 'SBMSInitializr'},
            folderSelector: {value: 'D:\\Personal\\Work\\WorkSpace\\ServiceClusterSimple', valid: false, action: this.setFolderSelectorValue},
            progressBar: {value: 0},
            actions : {action : {stopAllServiceButton : {}}},
            expandMainSection: {expand: false, action: this.toggleExpandState},
            ProcessTree: {},
            process: {
                toBeStarted : [],
                starting : [],
                completed : [],
                failed : []
            },
            sbmsWebSocket : {action : this.onRecieveFromServer}
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
                <FolderSelector value={this.state.folderSelector.value} valid={this.state.folderSelector.valid} action={this.state.folderSelector.action} style={folderSelectorStyle} />
                <Actions style={actionsStyle} clusterPath={this.state.folderSelector.value} action={this.state.actions.action}/>
            </div>}
            <div className='lp-line-div-container' style={lineDivStyle2}>
                <ProgressBar value={this.state.progressBar.value} style={progressBarStyle} />
                <ExpandMainSection expand={this.state.expandMainSection.expand} style={expandMainSectionStyle} action={this.state.expandMainSection.action} />
            </div>
            <MainSection processData={this.state.process} style={mainSectionStyle} />
            <SbmsWebSocket onRecieve={this.state.sbmsWebSocket.action} />
        </div>);
    }

    toggleExpandState() {
        var newState = this.state;
        newState.expandMainSection.expand = !newState.expandMainSection.expand;
        this.setState(newState);
    }

    setFolderSelectorValue(newValue, newValid) {
        let newState = this.state;
        newState.folderSelector.value = newValue;
        newState.folderSelector.valid = newValid;
        this.setState(newState);
    }

    onRecieveFromServer(event) {
        let messageFromServer = event.data;
        let newState = Helper.modifyState(messageFromServer, this.state);
        this.setState(newState);
    }

}

export default LandingPage;