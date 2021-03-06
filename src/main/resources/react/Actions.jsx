import React from 'react';
import PropTypes from "prop-types";
import StartAllServiceButton from './StartAllServiceButton.jsx';
import StopAllServiceButton from "./StopAllServiceButton.jsx";

class Actions extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        var actionsInnerStyle = {width:'100%',display:'inline-block',position:'relative',height:'inherit'};
        var startAllServiceStyle = {width:'fit-content',left:'5%',position:'absolute'};
        var stopAllServiceStyle = {width:'fit-content',left:'15%',position:'absolute'};

        return(<div className='actions-container' style={this.props.style}>
            <div className='actions-inner-container' style={actionsInnerStyle}>
                <StartAllServiceButton clusterPath={this.props.clusterPath} style={startAllServiceStyle} />
                <StopAllServiceButton clusterPath={this.props.clusterPath} action={this.props.action.stopAllServiceButton.action} style={stopAllServiceStyle}/>
            </div>
        </div>);

    }
}

Actions.propTypes = {clusterPath: PropTypes.string.isRequired};

export default Actions;