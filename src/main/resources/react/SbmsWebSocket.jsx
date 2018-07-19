import React from 'react';
import PropTypes from 'prop-types';

class SbmsWebSocket extends React.Component{

    constructor(props) {
        super(props);

    }

    render() {
        return null;
    };


    static getSocket() {

        let socket = SbmsWebSocket.socket;
        if (socket && socket.readyState === socket.OPEN)
            return socket;
        else {
            if(socket.readyState === socket.CLOSING || socket.readyState === socket.CLOSED)
                console.error('Socket has closed or is closing.');
            else
                console.error('Socket has not opened yet. Please try adding a delay before the call...');
            return null;
        }

    }


    static send(message)
    {
        let socket = SbmsWebSocket.getSocket();
        SbmsWebSocket.getSocket().send(message);
    }

    static executeAllCallBacksOnRecieve(event) {

        SbmsWebSocket.callbacksOnRecieve.forEach(n => n(event));
    }

    componentWillMount() {
        if(!SbmsWebSocket.socket)
            SbmsWebSocket.init();
        SbmsWebSocket.callbacksOnRecieve.push(this.props.onRecieve);
    }

}

SbmsWebSocket.callbacksOnRecieve = [];

SbmsWebSocket.init = function () {
    if (!SbmsWebSocket.socket) {
        SbmsWebSocket.socket = new WebSocket('ws://localhost:8080/webSocket');
        SbmsWebSocket.socket.onmessage = SbmsWebSocket.executeAllCallBacksOnRecieve;
    }
    
}();


SbmsWebSocket.propTypes = {onRecieve:PropTypes.func.isRequired};


export default SbmsWebSocket;