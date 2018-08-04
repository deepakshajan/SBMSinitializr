import React from 'react';
import PropTypes from 'prop-types';

class ExpandButton extends React.Component {

    constructor(props) {
        super(props);

        this.onClick = this.onClick.bind(this);
    }

    render() {
        let innerContainerStyle = {backgroundColor:'lightgreen',width:'inherit',margin:5,height:'inherit'};
        let buttonStyle = {height:"inherit"};
        let iconStyle = {color:'green',height:'inherit'};

        return(
            <div className='expand-button-container' style={this.props.style}>
                <div className='expand-button-inner-container' style={innerContainerStyle}>
                    <button className='expand-button' onClick={this.onClick} style={buttonStyle} title={this.props.expand?'Expand':'Collapse'} readOnly={this.props.disabled}>
                        {this.props.expand && <i className="fa fa-plus" style={iconStyle}></i>}
                        {!this.props.expand && <i className="fa fa-minus" style={iconStyle}></i>}
                    </button>
                </div>
            </div>
        );
    }

    onClick() {
        if(!this.props.disabled)
            this.props.action();
    }
}

ExpandButton.defaultProps = {expand: true, disabled: false};

ExpandButton.propTypes = {expand: PropTypes.bool.isRequired, disabled: PropTypes.bool};

export default ExpandButton;