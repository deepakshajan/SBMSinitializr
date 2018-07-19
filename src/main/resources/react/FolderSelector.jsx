import React from "react";
import PropTypes from "prop-types";
import ReactDOM from "react-dom";

class FolderSelector extends React.Component {

    constructor(props) {
        super(props);

        this.validateAndSetState = this.validateAndSetState.bind(this);
    }

    render(){
        var fsStyleInner = {position:'relative',width:'100%'};
        var fsInputContainerStyle = {width:'97%',display:'inline-block'};
        var labelDivStyle = {width:'10%',display:'inline-block'};
        var labelStyle = {fontWeight:'bold',width:'100%'};
        var inputDivStyle = {width:'90%',display:'inline-block'};
        var inputStyle = {borderColor:'lightgreen',width: '100%'};
        var validationDivStyle = {width:'2%',display:'inline-block',float:'right'};
        var tickDefaultStyle = {fontWeight:'bolder',color:'green',margin:'unset',cursor:'default'};
        var crossDefaultStyle = {fontWeight:'bolder',color:'red',margin:'unset',cursor:'default'};

        return(<div className='folder-selector-container' style={this.props.style}>
            <div className='folder-selector-inner-container' style={fsStyleInner}>
                <div className='folder-selector-input-container' style={fsInputContainerStyle}>
                    <div style={labelDivStyle}>
                        <label htmlFor='folder-selector-input' style={labelStyle}>Directory : </label>
                    </div>
                    <div style={inputDivStyle}>
                        <input name='folder-selector-input' type='text' value={this.props.value} onChange={this.validateAndSetState} placeholder='Path to the micro service cluster(eg : D:\code\serviceCluster)' style={inputStyle}></input>
                    </div>
                </div>
                <div className='folder-selector-validation-status' style={validationDivStyle}>
                    {this.props.valid && <p id='folder-selector-tick' style={tickDefaultStyle} title='Valid Directory'>&#10004;</p>}
                    {!this.props.valid && <p id='folder-selector-cross' style={crossDefaultStyle} title='Invalid Directory'>&#10006;</p>}
                </div>
            </div>
        </div>);
    }

    validateAndSetState(event) {

        let valid = false;
        if (this.checkForValidDirectory(event))
            valid = true;
        else
            valid = false;

        this.props.action(event.target.value, valid);
    }

    checkForValidDirectory(event) {
        var validDirectoryRegEx = new RegExp("^[A-Z]:(\\\\[a-z|A-Z]{1,99}){1,99}$");
        var valid = validDirectoryRegEx.test(event.target.value.trim());
        return valid;
    }

}

FolderSelector.propTypes = {
    value: PropTypes.string.isRequired,
    valid: PropTypes.bool.isRequired
};

export default FolderSelector;