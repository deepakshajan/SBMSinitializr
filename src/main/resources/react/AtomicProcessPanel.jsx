import React from "react";
import PropTypes from "prop-types";
import ExpandButton from "./ExpandButton.jsx";
import LogViewerButton from "./LogViewerButton.jsx";
import ProcessPanelStatus from "./ProcessPanelStatus.jsx";

class AtomicProcessPanel extends React.Component {

    constructor(props) {
        super();
        this.state = {showChilds: props.showChilds};

        this.calculateColour = this.calculateColour.bind(this);
        this.calculateStatus = this.calculateStatus.bind(this);
        this.expandButtonAction = this.expandButtonAction.bind(this);
        this.hasChilds = this.hasChilds.bind(this);

    }

    render() {
        let atomicProcessPanelStyle = {width:'-webkit-fill-available'};
        let innerContainerStyle = {display:'table',width:'inherit',marginTop:5,height:'fit-content',position:'relative'};
        let innerWrapperStyle = {height:25, display:'table-row', verticalAlign:'middle',backgroundColor:'lightgreen', position:'relative'};
        let expandButtonStyle = {height:'inherit',display:'inline-block'};
        let titleDivStyle = {display:'inline-block',marginLeft:'2%',height:'inherit'};
        let titleSpanStyle = {verticalAlign:'middle',colour:'green',fontWeight:'bolder',fontSize:'medium'};
        let logViewerButtonStyle = {display:'inline-block'};
        let processPanelStatusStyle = {display:'inline-block', marginRight:30};
        let rightDivStyle = {display:'inline-block', right:'1%',position:'absolute', height:'inherit'};
        let childDivStyle = {width:'99%',paddingLeft:'1%'};

        return(
            <div className='atomic-process-panel-container' style={this.props.style}>
                <div className='atomic-process-panel-inner-container' style={innerContainerStyle}>
                    <div className='atomic-process-panel-inner-wrapper' style={innerWrapperStyle}>
                        <ExpandButton style={expandButtonStyle} action={this.expandButtonAction} expand={!this.state.showChilds} disabled={!this.hasChilds()} />
                        <div className='atomic-process-panel-title' style={titleDivStyle}>
                            <span style={titleSpanStyle}>{this.props.title}</span>
                        </div>
                        {!this.props.isRoot &&
                        <div className='atomic-process-panel-right-div' style={rightDivStyle}>
                            <ProcessPanelStatus value={this.calculateStatus()} colour={this.calculateColour()} style={processPanelStatusStyle}/>
                            <LogViewerButton style={logViewerButtonStyle}/>
                        </div> }
                    </div>
                    {this.state.showChilds && <div className='atomic-process-panel-children' style={childDivStyle}>
                        {this.props.processList.map((value,key)=><AtomicProcessPanel key={key} title={value.value.toString()} processDetails={this.props.processDetails} processList={value.children} showChilds={false}
                                                                                     style={atomicProcessPanelStyle}/>)}
                    </div>}
                </div>
            </div>
        );
    }

    calculateStatus() {

        //TODO return if any condition sets status
        let status = "Waiting";
        let processName = this.props.title;
        if(this.props.processDetails.starting.includes(processName))
            status = "Executing";
        else if(this.props.processDetails.completed.includes(processName))
            status = "Success";
        else if(this.props.processDetails.failed.includes(processName))
            status = "Failed";
        else if(this.props.processDetails.toBeStarted.includes(processName))
            status = "Waiting";

        return status;
    }

    calculateColour() {

        let colour = "white";
        let processName = this.props.title;
        if(this.props.processDetails.starting.includes(processName))
            colour = "blue";
        else if(this.props.processDetails.completed.includes(processName))
            colour = "darkgreen";
        else if(this.props.processDetails.failed.includes(processName))
            colour = "red";
        else if(this.props.processDetails.toBeStarted.includes(processName))
            colour = "white";

        return colour;

    }

    expandButtonAction() {
        let newState = this.state;
        newState.showChilds = !newState.showChilds;
        this.setState(newState);
    }

    hasChilds() {
        return this.props.processList && this.props.processList.length > 0 ? true : false;
    }

}

AtomicProcessPanel.propTypes = {title: PropTypes.string.isRequired, isRoot: PropTypes.bool, processList: PropTypes.array.isRequired,
    showChilds: PropTypes.bool, processDetails: PropTypes.object.isRequired};

AtomicProcessPanel.defaultProps = {title: 'Title', isRoot: false, showChilds: false};

export default AtomicProcessPanel;