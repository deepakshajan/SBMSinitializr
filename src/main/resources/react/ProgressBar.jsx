import React from "react";
import PropTypes from "prop-types";

class ProgressBar extends React.Component {

    constructor(props) {
        super(props);
    }


    render() {
        var pbContainerStyle = {width:'100%', height:'inherit',position:'relative'};
        var pbValueStyle = {display:'inline-block',height:'inherit',position:'absolute',textAlign:'center',left:'50%'};
        var pbCompletedStyle = {width:this.props.value+'%',height:'inherit',backgroundColor:'lightgreen',display:'inline-block'};
        var pbRemainingStyle = {width:(100-this.props.value)+'%',height:'inherit',backgroundColor:'white',display:'inline-block'};
        var pbLabelDivStyle = {width:'10%',display:'inline-block', height:'inherit'};
        var pbLabelStyle = {fontWeight:'bold',width:'100%',display:'inline'};
        var pbInnerContainerStyle = {width: '89%',height:'inherit',display: 'inline-block',borderStyle:'groove',borderColor:'lightgreen'};

        return(
            <div id='pb-progress-bar' className='pb-progress-bar' style={this.props.style}>
                <div id='pb-container' className='pb-container' style={pbContainerStyle}>
                    <div className='pb-progress-bar-label' style={pbLabelDivStyle}>
                        <p style={pbLabelStyle}>Progress : </p>
                    </div>
                    <div className='pb-progress-bar-graphic' style={pbInnerContainerStyle}>
                        <div className='pb-progress-value' style={pbValueStyle}>{this.props.value+'%'}</div>
                        <div className='pb-completed' style={pbCompletedStyle}></div>
                        <div className='pb-remaining' style={pbRemainingStyle}></div>
                    </div>
                </div>
            </div>
        );
    }
}

ProgressBar.defaultProps = {value:0};

ProgressBar.propTypes = {
    value : PropTypes.number.isRequired
};

export default ProgressBar;