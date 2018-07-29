import React from 'react';

class ExpandButton extends React.Component {

    constructor(props) {
        super(props);

    }

    render() {
        let innerContainerStyle = {backgroundColor:'lightgreen',width:'inherit',margin:5,height:'inherit'};
        let buttonStyle = {height:"inherit"};
        let iconStyle = {color:'green',height:'inherit'};

        return(
            <div className='expand-button-container' style={this.props.style}>
                <div className='expand-button-inner-container' style={innerContainerStyle}>
                    <button className='expand-button' style={buttonStyle} title='Expand'>
                        <i className="fa fa-plus" style={iconStyle}></i>
                    </button>
                </div>
            </div>
        );
    }
}

export default ExpandButton;