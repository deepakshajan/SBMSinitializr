import React from 'react';
import PropTypes from 'prop-types';
import SbmsWebSocket from "./SbmsWebSocket.jsx";

class StartAllServiceButton extends React.Component {

    constructor(props) {
        super(props);

        this.onClick = this.onClick.bind(this);
    }

    render() {
        var innerContainerStyle = {height:'inherit',height:'inherit',display:'table-cell',verticalAlign:'middle'};
        var iconStyle = {color:'green',height:'inherit'};
        var buttonStyle = {height:"inherit",backgroundColor:'lightgreen'};

        return(<div className='start-all-service-button-container' style={this.props.style}>
            <div className='start-all-service-button-inner-container' style={innerContainerStyle}>
                <button className='start-all-service-icon-button' onClick={this.onClick} style={buttonStyle} title='Start all services'>
                    <i className="fas fa-play" style={iconStyle}></i>
                </button>
            </div>
        </div>);
    }

    onClick() {
        SbmsWebSocket.send(this.getProcessTreeMessage());
        SbmsWebSocket.send(this.getStartAllModulesMessage());
    }

    getStartAllModulesMessage() {

        let request = {
            "endPoint" : "/initializr/deploy",
            "clusterPath" : this.props.clusterPath.trim(),
            "buildType" : "maven",
            "runClean" : false,
            "runTests" : false,
            "runBoot" : true
        };

        let requestAsString = JSON.stringify(request);
        return requestAsString;
    }

    getProcessTreeMessage() {

        let request = {
            "endPoint" : "/initializr/getProcessTree",
            "clusterPath" : this.props.clusterPath.trim()
        };

        let requestAsString = JSON.stringify(request);
        return requestAsString;
    }
}

StartAllServiceButton.propTypes = {clusterPath: PropTypes.string.isRequired};

export default StartAllServiceButton;