import React from 'react';

class StartAllServiceButton extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var innerContainerStyle = {height:'inherit',height:'inherit',display:'table-cell',verticalAlign:'middle'};
        var iconStyle = {color:'green',height:'inherit'};
        var buttonStyle = {height:"inherit",backgroundColor:'lightgreen'};

        return(<div className='start-all-service-button-container' style={this.props.style}>
            <div className='start-all-service-button-inner-container' style={innerContainerStyle}>
                <button className='start-all-service-icon-button' style={buttonStyle} title='Start all services'>
                    <i className="fas fa-play" style={iconStyle}></i>
                </button>
            </div>
        </div>);
    }
}

export default StartAllServiceButton;