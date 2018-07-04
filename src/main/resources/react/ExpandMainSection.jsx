import React from 'react';

class ExpandMainSection extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        var innerContainerStyle = {height:'inherit',height:'inherit',display:'table-cell',verticalAlign:'middle'};
        var iconStyle = {color:'green',height:'inherit'};
        var buttonStyle = {height:"inherit",backgroundColor:'lightgreen'};

        return(<div className='expand-main-section-container' style={this.props.style}>
            <div className='expand-main-section-inner-container' style={innerContainerStyle}>
                <button className='icon-button' style={buttonStyle} title='Focus Mode'>
                    <i className="fas fa-expand" style={iconStyle}></i>
                </button>
            </div>
        </div>);
    }
}

export default ExpandMainSection;