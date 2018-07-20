import React from "react";
import SbmsWebSocket from "./SbmsWebSocket.jsx";

class StopAllServiceButton extends React.Component {

    constructor(props) {
        super(props);

        this.onClick = this.onClick.bind(this);
    }

    render() {
        var innerContainerStyle = {height:'inherit',height:'inherit',display:'table-cell',verticalAlign:'middle'};
        var iconStyle = {color:'green',height:'inherit'};
        var buttonStyle = {height:"inherit",backgroundColor:'lightgreen'};

        return(<div className='stop-all-service-button-container' style={this.props.style}>
            <div className='stop-all-service-button-inner-container' style={innerContainerStyle}>
                <button className='stop-all-service-icon-button' onClick={this.onClick} style={buttonStyle} title='Stop all services'>
                    <i className="fas fa-stop" style={iconStyle}></i>
                </button>
            </div>
        </div>);
    };

    onClick() {
        SbmsWebSocket.send(this.getStopAllModulesMessage());
    }

    getStopAllModulesMessage() {

        let request = {
            "endPoint" : "/initializr/stopAllProcess"
        };

        let requestAsString = JSON.stringify(request);
        return requestAsString;
    }
}

export default StopAllServiceButton;