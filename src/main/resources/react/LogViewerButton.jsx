import React from 'react';

class LogViewerButton extends React.Component {

    constructor(props) {
        super(props)

        this.onClick = this.onClick.bind(this);
    }

    render() {
        let innerContainerStyle = {backgroundColor:'lightgreen',width:'inherit',margin:5,height:'inherit'};
        let buttonStyle = {height:"inherit"};
        let iconStyle = {color:'green',height:'inherit'};

        return(
            <div className='log-viewer-button-container' style={this.props.style}>
                <div className='log-viewer-button-inner-container' style={innerContainerStyle}>
                    <button className='log-viewer-button' onClick={this.onClick} style={buttonStyle} title='View Log'>
                        <i className="fa fa-file" style={iconStyle}></i>
                    </button>
                </div>
            </div>
        );
    }

    onClick() {
        alert('Please refer {installation_path}/output/ for logs...');
    }
}

export default LogViewerButton;