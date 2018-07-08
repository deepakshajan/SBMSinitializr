import React from 'react';
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
        SbmsWebSocket.send('Message from StartAllServiceButton');
    }
}

export default StartAllServiceButton;