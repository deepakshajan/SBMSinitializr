import React from "react";
import PropTypes from "prop-types";
import ExpandButton from "./ExpandButton.jsx";
import LogViewerButton from "./LogViewerButton.jsx";
import ProcessPanelStatus from "./ProcessPanelStatus.jsx";

class AtomicProcessPanel extends React.Component {

    constructor(props) {
        super();

        this.calculateColour = this.calculateColour.bind(this);

    }

    render() {
        let innerContainerStyle = {display:'table',backgroundColor:'lightgreen',width:'inherit',margin:10,height:25};
        let innerWrapperStyle = {display:'table-cell', verticalAlign:'middle'};
        let expandButtonStyle = {height:'inherit',display:'inline-block'};
        let titleDivStyle = {display:'inline-block',marginLeft:'2%'};
        let titleSpanStyle = {verticalAlign:'middle',colour:'green',fontWeight:'bolder',fontSize:'medium'};
        let logViewerButtonStyle = {display:'inline-block'};
        let processPanelStatusStyle = {display:'inline-block', marginRight:30};
        let rightDivStyle = {display:'inline-block', left:'79%',position:'relative', height:'inherit'};

        return(
            <div className='atomic-process-panel-container' style={this.props.style}>
                <div className='atomic-process-panel-inner-container' style={innerContainerStyle}>
                    <div className='atomic-process-panel-inner-wrapper' style={innerWrapperStyle}>
                        <ExpandButton style={expandButtonStyle}/>
                        <div className='atomic-process-panel-title' style={titleDivStyle}>
                            <span style={titleSpanStyle}>{this.props.title}</span>
                        </div>
                        <div className='atomic-process-panel-right-div' style={rightDivStyle}>
                            <ProcessPanelStatus value={this.props.status} colour={this.calculateColour()} style={processPanelStatusStyle}/>
                            {this.props.showLogButton && <LogViewerButton style={logViewerButtonStyle}/>}
                        </div>
                    </div>
                </div>
            </div>
        );
    }

    calculateColour() {
        return "blue";
    }

}

AtomicProcessPanel.propTypes = {title: PropTypes.string.isRequired, showLogButton: PropTypes.bool};

AtomicProcessPanel.defaultProps = {title: 'Title', showLogButton: true};

export default AtomicProcessPanel;